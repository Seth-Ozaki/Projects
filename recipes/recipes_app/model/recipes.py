from recipes_app.config.mysqlconnection import connectToMySQL
from flask import flash
from recipes_app.model.users import User


class Recipe:
    DB = 'recipes'
    def __init__( self , data ):
        self.id = data['id']
        self.name = data['name']
        self.under = bool(data['under'])
        self.description = data['description']
        self.instructions = data['instructions']
        self.date_made = data['date_made']
        self.user_id = data['user_id']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']
        self.user=None

    @staticmethod
    def clear():
        query= "SET SQL_SAFE_UPDATES = 0 "
        connectToMySQL(Recipe.DB).query_db(query)
        query = "ALTER TABLE recipes AUTO_INCREMENT = 0;"
        connectToMySQL(Recipe.DB).query_db(query)
        query= "DELETE FROM recipes WHERE name IS NOT null;"
        connectToMySQL(Recipe.DB).query_db(query)

    @staticmethod
    def validate_recipe(recipe):
        print(recipe)
        is_valid = True
        if len(recipe['name']) < 1:
            flash("There must be a recipe name", "add_recipe")
            is_valid = False
        if len(recipe['description']) < 1:
            flash("There must be a description", "add_recipe")
            is_valid = False
        if len(recipe['description']) > 255:
            flash("The description is too long", "add_recipe")
            is_valid = False
        if len(recipe['instructions']) < 1:
            flash("There must be instructions", "add_recipe")
            is_valid = False
        if 'under' not in recipe.keys():
            flash("You must tell us if it can be cooked under 30 minutes", "add_recipe")
            is_valid = False
        if (recipe['date_made'] == ''):
            flash("there must be a date made", "add_recipe")
            is_valid = False
        return is_valid
        

    @classmethod
    def add_recipe(cls, data):
        query = """INSERT INTO recipes (name, under, description, instructions, date_made, user_id)
                VALUES (%(name)s,%(under)s,%(description)s,%(instructions)s,%(date_made)s,%(user_id)s);"""
        result = connectToMySQL(Recipe.DB).query_db(query,data)
        return result
    
    @classmethod
    def get_recipe(cls, id):
        query = "SELECT * FROM recipes JOIN users ON users.id = recipes.user_id WHERE recipes.id = %(id)s"
        results = connectToMySQL(User.DB).query_db(query,{"id":id})
        print(results)
        recipe = cls(results[0])
        temp_user = {
                "id" : results[0]["users.id"],
                "first_name" : results[0]["first_name"],
                "last_name" : results[0]["last_name"],
                "email" : results[0]["email"],
                "password" : results[0]["password"],
                "created_at" : results[0]["users.created_at"],
                "updated_at" : results[0]["users.updated_at"]
            }
        recipe.user = User(temp_user)
        return recipe
    
    @classmethod
    def get_users_recipes( cls ):
        query = "SELECT * FROM recipes JOIN users ON users.id = recipes.user_id"
        
        results = connectToMySQL(Recipe.DB).query_db( query )

        recipes = []
        for recipe in results:
            new_recipe = cls(recipe)
            temp_user = {
                "id" : recipe["users.id"],
                "first_name" : recipe["first_name"],
                "last_name" : recipe["last_name"],
                "email" : recipe["email"],
                "password" : recipe["password"],
                "created_at" : recipe["users.created_at"],
                "updated_at" : recipe["users.updated_at"]
            }
            new_recipe.user = User(temp_user)
            recipes.append( new_recipe )
        return recipes
    
    @classmethod
    def delete_recipe(cls, id):
        query = "DELETE FROM recipes WHERE id = %(id)s;"
        result = connectToMySQL(Recipe.DB).query_db(query,{"id":int(id)})
        return result
    
    @classmethod
    def submit_edit(cls, data):
        query = """UPDATE recipes
                    SET name = %(name)s,under = %(under)s,description = %(description)s,instructions = %(instructions)s,date_made = %(date_made)s
                    WHERE id = %(id)s;"""
        result = connectToMySQL(Recipe.DB).query_db(query, data)
        return result