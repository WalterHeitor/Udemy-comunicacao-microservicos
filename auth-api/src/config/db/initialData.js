import bcrypt from "bcrypt";
import User from "../../modules/user/model/User.js";

export async function createInitialData() {
    try {
        await User.sync({ force: true });
    let password = await bcrypt.hash('123456', 10);

    await User.create({
        name: 'User Test1',
        email: 'userTest1@email.com',
        password: password,
    });

    await User.create({
        name: 'User Test2',
        email: 'userTest2@email.com',
        password: password,
    });

    } catch (error) {
        console.log(error)
    }
}
