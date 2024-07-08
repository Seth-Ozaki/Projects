# Recipe Tracker

To create and keep track of your recipes

## Description

This project was made during my time with Coding Dojo, The purpose is to allow a user with their own account to be able to create a recipe that everyone can see as long as they are logged into an account. There are form validations, insures user session, only the creator can edit/delete their own recipes.

## Getting Started

### Dependencies

* Python version 3.12.2 or higher
* Flask
* Flask Bcrypt
* Pipenv (Optional)
* Jinja2
* MySQL Workbench
* BootStrap


### Executing program (VS Code)

* Must have Python, flask, and flask bcrypt installed
* cd into the correct file path
* being within a pipenv is optional
* run commands below without being in a pipenv shell (python3 for Mac users)
```
python -V (checks python version)
pip list (checks for dependencies needed)
pip install flask flask_bcrypt (If dependencies needed are not present)
python .\server.py
```
* run commands below in a pipenv shell
```
python -V (checks python version)
pip install pipenv
pipenv shell (activates shell)
pip install flask flask_bcrypt (these dependecies will only be within the pipenv shell)
python .\server.py
```

## Authors

Seth Ozaki

## Version History

* 0.1
    * Initial Release


## Acknowledgments

Inspiration, code snippets, etc.
* [Coding Dojo](https://www.codingdojo.com/)
