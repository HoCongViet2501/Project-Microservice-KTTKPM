const app = require('./app');
const config = require('./config')
const db = require('./models')

app.listen(config.port,() =>{
    console.log(`server running! ${config.port}`);
})
