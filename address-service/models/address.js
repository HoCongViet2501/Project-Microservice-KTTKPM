'use strict';

const { Model } = require('sequelize');

module.exports = (sequelize, DataTypes) => {
    class Address extends Model {}
    Address.init({
        id: {
            type: DataTypes.INTEGER,
            primaryKey: true,
            autoIncrement: true,
        },
        address: {
            type: DataTypes.STRING,
        },
        default: {
            type: DataTypes.TINYINT,
        },
        userId: {
            type: DataTypes.INTEGER,
        }
    }, {
        sequelize,
        tableName: 'address',
        createdAt: false,
        updatedAt: false
    });
    return Address;
}