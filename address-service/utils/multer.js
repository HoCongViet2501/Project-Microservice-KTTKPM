const multer = require('multer');
const path = require('path');

module.exports = multer({
    storage: multer.diskStorage({
        fileFilter: (req, res, cb) => {
            let ext = path.extname(file.originalname);
            if(ext != ".jpg" && ext !== ".jpeg" && ext !== ".png" && ext != ".PNG" && ext != ".JPG" && ext != ".JPEG"){
                cb(new Error("File type is not supported"), false);
                return;
            }
            cb(null,true);
        }
    }),
});
