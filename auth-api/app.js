import express from "express";

import * as db from "./src/config/db/initialData.js";
import userRoutes from "./src/modules/user/routers/UserRouters.js"
import checkToken from "./src/config/auth/checkToken.js";

const app = express()
const env = process.env
const PORT = env.PORT || 8080

db.createInitialData()
app.get('/api/status', (req, res) => {
    return res.status(200).json({
        service: "AUTH-API",
        status: "up",
        httpStatus: 200
    });
});
app.use(express.json());
app.use(userRoutes);


app.listen(PORT, () => {
    console.info(`server sarted successfully at port ${PORT}`);
});
