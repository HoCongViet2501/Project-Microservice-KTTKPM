const Address = require('../models/address');
const db = require('../models');

const createAddress = async (req, res) => {
  try {
    // TODO: Validate JWT and get user id
    const userId = 999;
    if (req.body.default) {
      await db.Address.update({
        default: 0
      }, { where: { userId: userId } });
    }
    const newAddress = await db.Address.create({
      address: req.body.address,
      default: req.body.default || 0,
      userId 
    })
    return res.status(200).json({
      status: 'success',
      data: newAddress
    });
  } catch (err) {
    console.log(err);
    return res.status(400).json({
      status: 'fail',
      message: err
    });
  }
}

const getAddresses = async (req, res) => {
  try {
    // TODO: Validate JWT and get user id
    const userId = 999;
    const addresses = await db.Address.findAll({ where: { userId: userId } }); 
    return res.status(200).json({
      status: 'success',
      data: addresses
    });
  } catch (err) {
    return res.status(400).json({
      status: 'fail',
      message: err
    });
  }
}

const getAddress = async (req, res) => {
  const id = req.params.id;
  try {
    // TODO: Validate JWT
    const address = await db.Address.findOne({ where: { id: id } });
    return res.status(200).json({
      status: 'success',
      data: address
    });
  } catch (err) {
    return res.status(400).json({
      status: 'fail',
      message: err
    });
  }
}

const updateAddress = async (req, res) => {
  // TODO: Validate JWT
  const id = req.params.id;
  try {
    const address = await db.Address.findOne({ where: { id: id } });
    if (address) {
      if (req.body.default) {
        await db.Address.update({
          default: 0
        }, { where: { userId: address.userId } });
      }
      const newAddress = await db.Address.update({
        address: req.body.address,
        default: req.body.default || 0,
      }, { where: { id: id } });
      return res.status(200).json({
        status: 'success',
        data: newAddress
      });
    }
  } catch (err) {
    return res.status(400).json({
      status: 'fail',
      message: err
    });
  }
}
const deleteAddress = async (req, res) => {
  const id = req.params.id;
  try {
    // TODO: Validate JWT
    await db.Address.destroy({ where: { id: id } });
    return res.status(200).json({
      status: 'success',
      message: 'Address deleted successfully'
    });
  } catch (err) {
    return res.status(400).json({
      status: 'fail',
      message: err
    });
  }
}
module.exports = {
  getAddress,
  getAddresses,
  createAddress,
  updateAddress,
  deleteAddress
}
