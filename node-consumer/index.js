
const { Validator, Cart, CartItem } = require('validator-lib');

const validator = new Validator(new Cart(
    new Array([
        new CartItem("av", 123.23),
        new CartItem("abcd", 45.32)
    ]),
    56.56
))

console.log(validator.isValid())