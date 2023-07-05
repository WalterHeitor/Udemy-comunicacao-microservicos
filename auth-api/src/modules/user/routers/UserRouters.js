import { Router } from "express";
import UserController from "../controller/userController.js";
import checkToken from "../../../config/auth/checkToken.js";

const router = new Router();

router.post('/api/users/auth', (req, res) => UserController.getAcessToken(req, res));
router.use(checkToken);
router.get('/api/users/email/:email', (req, res) => UserController.findByEmail(req, res));
export default router;
