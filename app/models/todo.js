var mongoose = require('mongoose');

module.exports = mongoose.model('Todo', {
    status: {
        type: String,
        default: ''
    },
    id: {
        type: String,
        default: ''
    },
    child: {
        type: [String],
        default: []
    },
    name: {
        type: String,
        default: ''
    }
});