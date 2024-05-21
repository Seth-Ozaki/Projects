import User from "../models/user.model.js";

//CRUD

const UserController = {

    //CREATE----------------------
    create: async (req, res) => {
        try {
            const newUser = await User.create(req.body);
            res.json(newUser);
        } catch (error) {
            console.log(error);
            res.status(400).json(error);
        }
    },

    //READ------------------------
    readAll: async (req, res) => {
        try {
            const allUsers = await User.find();
            res.json(allUsers);
        } catch (error) {
            console.log(error);
            res.status(400).json(error);
        }
    },

    readOne: async (req, res) => {
        try {
            const oneUser = await User.findById(req.params.id);
            res.json(oneUser);
        } catch (error) {
            console.log(error);
            res.status(400).json(error);
        }
    },

    //UPDATE-----------------------
    update: async (req, res) => {
        const options = {
            new: true,
            runValidators: true
        };
        try {
            const updatedUser = await User.findByIdAndUpdate(req.params.id, req.body, options);
            res.json(updatedUser);
        } catch (error) {
            console.log(error);
            res.status(400).json(error);
        }
    },

    //DELETE------------------------
    delete: async (req, res) => {
        try {
            const deletedUser = await User.findByIdAndDelete(req.params.id);
            res.json(deletedUser);
        } catch (error) {
            console.log(error);
            res.status(400).json(error);
        }
    }
};

export default UserController;