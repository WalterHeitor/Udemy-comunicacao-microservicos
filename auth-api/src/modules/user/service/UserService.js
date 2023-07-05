import bcrypt from "bcrypt";
import jwt from "jsonwebtoken"

import UserRepository from "../repository/UserRepository.js";
import UserException from "../exception/Exception.js";
import * as httpStatus from "../../../config/containts/httpStatus.js"
import * as secrets from "../../../config/containts/secrets.js"

class UserService {
    async findByEmail(req) {
        try {
            const { email } = req.params;
            const { authUser } = req;
            this.validDateRequest(email);
            let user = await UserRepository.findByEmail(email);
            this.validUserNotFound(user);
            this.validateAuthenticateUser(user, authUser);
            return {
                status: httpStatus.SUCCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email,
                }
            };
        } catch (error) {
            const status = error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR
            return {
                status: status,
                message: error.message
            };
        }
    }
    validDateRequest(email) {
        if (!email) {
            throw new UserException(
                httpStatus.BAD_REQUEST,
                'User e-mail was not informed.'
            );
        }
    }
    validUserNotFound(user) {
        if (!user) {
            throw new UserException(httpStatus.BAD_REQUEST, "user was not found.")
        }
    }
    validateAuthenticateUser(user, authUser) {
        if (!authUser || (user.id !== authUser.id)) {
            throw new UserException(
                httpStatus.FORBIDDEN,
                "You not see this user data."
            );
        }
    }
    async getAccessToken(req) {

        try {
            const { email, password } = req.body;
            this.validAcessTokenData(email, password);
            let user = await UserRepository.findByEmail(email);
            this.validUserNotFound(user);
            await this.validatePassword(password, user.password);
            const authUser = { id: user.id, name: user.name, email: user.email };
            const accessToken = jwt.sign({ authUser }, secrets.API_SECRET, {
                expiresIn: "1d",
            });
            return {
                status: httpStatus.SUCCESS,
                accessToken,
            };
        } catch (error) {
            const status = error && error.status ? error.status : httpStatus.INTERNAL_SERVER_ERROR;
            return {
                status,
                message: error.message
            };
        }
    }

    validAcessTokenData(email, password) {
        if (!email || !password) {
            throw new UserException(
                httpStatus.UNAUTHORIZED,
                "Email and password must be informed."
            )
        }
    }

    async validatePassword(password, hashPassword) {
        if (!bcrypt.compare(password, hashPassword)) {
            throw new UserException(httpStatus.UNAUTHORIZED, "Password doesn´t match.");
        }
    }
}
export default new UserService;