import express from "express";

const app = express();
const env = process.env;
const PORT = env.PORT || 8083;

app.get("/api/status", (req, res) => {
    return res.status(200).json({
        service: "Sales-API",
        status: "up",
        httpStatus: 200,
    })
})

app.listen(PORT, () => {
    console.info(`server started successsfuly at port ${PORT}`);
});
