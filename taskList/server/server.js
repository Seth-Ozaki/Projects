import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import dbConnect from "./config/mongoose.config.js";
import router from "./routes/user.routes.js";
const app = express();
//call this first
dotenv.config();

//MIDDLEWARE
app.use(express.json(), cors());
app.use("/api", router);
//access .env variables
const PORT = process.env.PORT;

//Access remote DB
const DB_NAME = "taskDB";
dbConnect(DB_NAME);

app.listen(PORT, () =>
    console.log(`>>> Listening on port: ${PORT}`)
);