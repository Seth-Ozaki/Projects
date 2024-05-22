import { Router } from 'express';
import UserController from '../controllers/user.controller.js';


const router = Router();

router.route("/users")
    .get(UserController.readAll)
    .post(UserController.create);

router.route("/users/:id")
    .delete(UserController.delete)
    .get(UserController.readOne)
    .put(UserController.update);

router.route("/users/task/:id")
    .put(UserController.updateTaskArray);
export default router;