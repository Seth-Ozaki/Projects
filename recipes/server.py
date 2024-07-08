from recipes_app.controller import routes
from recipes_app import app
from flask import Flask



if __name__=='__main__':
    app.run(debug=True)