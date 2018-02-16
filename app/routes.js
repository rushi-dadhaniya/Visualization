var Todo = require('./models/todo');

var MongoClient = require('mongodb').MongoClient;
var assert = require('assert');
var ObjectId = require('mongodb').ObjectID;
var url = 'mongodb://localhost:27017/workflow';

var insertDocument = function(db, collection_name, data, callback) {
   db.collection(collection_name).insertOne( data, function(err, result) {
    assert.equal(err, null);
    callback();
  });
};

var getAllDocuments = function(db, collection_name, callback) {
   var result = [];
   var cursor =db.collection(collection_name).find( );
   cursor.each(function(err, doc) {
      assert.equal(err, null);
      if (doc != null) {
         result.push(doc);
      } else {
         callback(result);
      }
   });
};

module.exports = function (app) {

    app.get('/api/getJobs', function (req, res) {
        MongoClient.connect(url, function(err, db) {
          assert.equal(null, err);
          getAllDocuments(db,'workflow_data', function(result) {
              db.close();
              res.json(result);
          });
        });
    });

    app.post('/api/workflow_template', function (req, res) {
        MongoClient.connect(url, function(err, db) {
          assert.equal(null, err);
          var data = req.body;
          insertDocument(db,'workflow_template', data, function() {
              db.close();
              res.json({'status': 'success'});
          });
        });
    });



    // application -------------------------------------------------------------
    app.get('*', function (req, res) {
        res.sendFile(__dirname + '/public/index.html'); // load the single view file (angular will handle the page changes on the front-end)
    });
};
