from recipes_app.config.mysqlconnection import connectToMySQL
from flask import flash
import re

EMAIL_REGEX = re.compile(r'^[a-zA-Z0-9.+_-]+@[a-zA-Z0-9._-]+\.[a-zA-Z]+$') 

class User:
    DB = 'recipes'
    def __init__( self , data ):
        self.id = data['id']
        self.first_name = data['first_name']
        self.last_name = data['last_name']
        self.email = data['email']
        self.password = data['password']
        self.created_at = data['created_at']
        self.updated_at = data['updated_at']
        self.recipes=[]

    @staticmethod
    def clear():
        query= "SET SQL_SAFE_UPDATES = 0 "
        connectToMySQL(User.DB).query_db(query)
        query = "ALTER TABLE users AUTO_INCREMENT = 0;"
        connectToMySQL(User.DB).query_db(query)
        query= "DELETE FROM users WHERE first_name IS NOT null;"
        connectToMySQL(User.DB).query_db(query)



    @staticmethod
    def validate(user):
        is_valid = True
        if len(user['first_name']) < 1:
            flash("You must have a first name.", "register")
            is_valid = False
        if len(user['last_name']) < 1:
            flash("You must have a last name", "register")
            is_valid = False
        if len(user['email']) < 3:
            flash("the email is not long enough", "register")
            is_valid = False
        if len(user['password']) < 8:
            flash("password must be at least 8 characters.", "register")
            is_valid = False
        if (user['password'] != user['confirm_password']):
            flash("passwords must match.", "register")
            is_valid = False
        if not EMAIL_REGEX.match(user['email']): 
            flash("Invalid email address!", "register")
            is_valid = False
        return is_valid
    

    @classmethod
    def register(cls, data):
        query = """INSERT INTO users (first_name, last_name, email, password)
                    VALUES (%(first_name)s,%(last_name)s,%(email)s,%(password)s);"""
        result = connectToMySQL(User.DB).query_db(query,data)
        return result
    
    @classmethod
    def get_by_email(cls,data):
        query = "SELECT * FROM users WHERE email = %(email)s;"
        results = connectToMySQL(User.DB).query_db(query,data)
        if len(results) < 1:
            return False
        return cls(results[0])
    
    @classmethod
    def get_user(cls, id):
        query = "SELECT * FROM users WHERE id = %(id)s;"
        results = connectToMySQL(User.DB).query_db(query,{"id":id})
        users = []
        for user in results:
            users.append( cls(user) )
        return users[0]
    
    