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
            type: DataTypes.BOOLEAN,
        },
        userId: {
            type: DataTypes.INTEGER,
            field: 'user_id',
        }
    }, {
        sequelize,
        tableName: 'address',
        createdAt: false,
        updatedAt: false,
        underscored: true,
    });
    return Address;
}