const express = require('express');
const cors = require('cors');
const addressRouter = require('./routers/address');
const app = express();
app.use(cors());
app.use(express.json());
app.use('/addresses',addressRouter);
app.get('/health', (req, res) => res.status(200).send('UP'));
module.exports = app;
