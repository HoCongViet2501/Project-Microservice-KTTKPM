const express = require('express');
const router = express.Router();

const { getAddress, getAddresses, createAddress, updateAddress, deleteAddress } = require('../controllers/address');

// TODO: auth middleware
router.post('/', createAddress);
router.get('/', getAddresses);
router.get('/:id', getAddress);
router.put('/:id', updateAddress);
router.delete('/:id', deleteAddress);

module.exports = router;