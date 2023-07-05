import { Router } from "express";
import UserController from "../controller/userController.js";

const router = new Router();
router.get('/api/users/email/:email', (req, res) => UserController.findByEmail(req, res));
router.post('/api/users/auth', (req, res) => UserController.getAcessToken(req, res));

export default router;
