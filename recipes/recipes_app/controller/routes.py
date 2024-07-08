from recipes_app import app
from flask import render_template, request, redirect, session, flash
from flask_bcrypt import Bcrypt
from recipes_app.model.users import User
from recipes_app.model.recipes import Recipe
bcrypt=Bcrypt(app)

@app.route('/')
def start():
    Recipe.clear()
    User.clear()
    return render_template('home.html')


@app.route('/home')
def home():
    return render_template('home.html')

@app.route('/clearcookie')
def clearcookie():
    session.clear()
    return redirect ('/home')

@app.route('/register', methods=['POST'])
def register():
    data = {"email": request.form["email"]}
    user_in_db = User.get_by_email(data)
    if user_in_db:
        flash("Email Already Exists!", "register")
        return redirect('/home')
    if not User.validate(request.form):
        return redirect('/home')
    pw_hash=bcrypt.generate_password_hash(request.form['password'])
    data = {
        "first_name":request.form['first_name'],
        "last_name":request.form['last_name'],
        "email":request.form['email'],
        "password": pw_hash
    }
    User.register(data)
    user_in_db = User.get_by_email(data)
    session['user_id'] = user_in_db.id
    session['name'] = user_in_db.first_name
    return redirect(f'/logged/{session['user_id']}')


@app.route('/login', methods=['POST'])
def login():
    data = {"email": request.form["email"]}
    user_in_db = User.get_by_email(data)
    if not user_in_db:
        flash("Invalid Email", "login")
        return redirect('/home')
    if not bcrypt.check_password_hash(user_in_db.password, request.form['password']):
        flash("Invalid Password", "login")
        return redirect('/home')
    
    session['user_id'] = user_in_db.id
    session['name'] = user_in_db.first_name
    return redirect(f'/logged/{session['user_id']}')

@app.route('/logged/<int:id>')
def logged(id):
    user = User.get_user(id)
    recipes = Recipe.get_users_recipes()
    return render_template('logged.html', one_user = user, all_recipes= recipes)


@app.route('/new_recipe/<int:id>')
def new_recipe(id):
    user = User.get_user(id)
    return render_template('new.html', one_user=user)

@app.route('/add_recipe/<int:id>', methods=['POST'])
def add_recipe(id):
    if not  Recipe.validate_recipe(request.form):
        return redirect(f'/new_recipe/{id}')
    data = {
        "name":request.form['name'],
        "under":request.form['under'],
        "description":request.form['description'],
        "instructions":request.form['instructions'],
        "date_made":request.form['date_made'],
        "user_id":id
    }
    Recipe.add_recipe(data)
    return redirect(f'/logged/{id}')

@app.route('/recipe/<int:id>')
def recipe(id):
    recipe = Recipe.get_recipe(id)
    return render_template('recipe.html', one_recipe = recipe)

@app.route('/delete_recipe/<int:id>')
def delete_recipe(id):
    Recipe.delete_recipe(id)
    return redirect(f'/logged/{session['user_id']}')

@app.route('/edit/<int:id>')
def edit(id):
    recipe = Recipe.get_recipe(id)
    return render_template('edit.html', one_recipe = recipe)

@app.route('/submit_edit/<int:id>', methods=['POST'])
def submit_edit(id):
    if not  Recipe.validate_recipe(request.form):
        return redirect(f'/edit/{id}')
    Recipe.submit_edit(request.form)
    return redirect(f'/recipe/{id}')