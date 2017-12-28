var express = require('express');
var app = express();
var server = require('http').Server(app);
var io = require('socket.io')(server);
var bodyParser = require('body-parser');
var multer = require('multer'); 
var upload = multer(); 

app.use(express.static(__dirname));
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));


server.listen(4000, function(){
    console.log('Listening at port 4000');
});
var allowCrossDomain = function(req, res, next) {
    res.header('Access-Control-Allow-Origin', '*');
    res.header('Access-Control-Allow-Methods', 'GET,PUT,POST,DELETE,OPTIONS');
    res.header('Access-Control-Allow-Headers', 'Content-Type, Authorization, Content-Length, X-Requested-With');

    // intercept OPTIONS method
    if ('OPTIONS' == req.method) {
      res.send(200);
    }
    else {
      next();
    }
};
app.use(allowCrossDomain);

io.sockets.on('connection', function (socket) {
    
    var _socket = socket;
    
    console.log('Server connected');

    /*Sample structure for request from Backend server
	{
		"userName":"lavanya",
		"state":"Active"
	}
    */

    //catch from server Activate
    socket.on('activateEvent', function (dataLogin, callback) {
        console.log("Activate message from server");

	    app.post('/activateResponseHandler', function(req, res){
	    	console.log('------');
	    	//send
	    	socket.emit('activateResponseHandler',function(req){
	        	console.log('successActivate Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});
    //External Force to Activate
	app.post('/activateResponseHandler', function(req, res){
		// console.log('---activate res event---Recived Status: '+req.body.state);
	    var stat = req.body.state;
	    //send
		socket.emit('activateResponseHandler',function(stat){
		   	console.log('activateResponseHandler Emitted from server ');
		})
		res.send('Response received');
	});

	//Login Event
	socket.on('loginEvent', function (dataLogin, callback) {
	    app.post('/loginResponseEvent', function(req, res){
	    	//send
	    	socket.emit('loginResponseEvent',function(req){
	        	console.log('loginResponseEvent Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});
	//External Force to Login
	app.post('/loginResponseEvent', function(req, res){
		console.log('---activate res event---');
	    var stat = req.body.state;
	    //send
		socket.emit('loginResponseEvent',function(stat){
		   	console.log('loginResponseEvent Emitted from server ');
		})
		res.send('Response received');
	});

	//Logout Event
	socket.on('logoutEvent', function (dataLogin, callback) {
        console.log("logoutEvent message from server");
	    app.post('/logoutResponseEvent', function(req, res){
	    	var stat = req.body.state;
	    	//send
	    	socket.emit('logoutResponseEvent',function(stat){
	        	console.log('logoutResponseEvent Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});
	

	//Deactivate Event
	socket.on('deactivateEvent', function (dataLogin, callback) {
        console.log("deactivateEvent message from server");

	    app.post('/deactivateResponseEvent', function(req, res){
	    	var stat = req.body.state;
	    	//send
	    	socket.emit('deactivateResponseEvent',function(stat){
	        	console.log('deactivateResponseEvent Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});

	//Ready Event
	socket.on('readyEvent', function (dataLogin, callback) {
        console.log("readyEvent message from server");
	    app.post('/readyResponseEvent', function(req, res){
	    	var stat = req.body.state;
	    	//send
	    	socket.emit('readyResponseEvent',function(stat){
	        	console.log('readyResponseEvent Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});
	//External Force to Ready
	app.post('/readyResponseEvent', function(req, res){
	    console.log('---readyResponseEvent res event---');
	    var stat = req.body.state;
	    //send
		socket.emit('readyResponseEvent',function(stat){
		   	console.log('readyResponseEvent Emitted from server ');
		})
		res.send('Response received');
	});

	//NotReady Event
	socket.on('notreadyEvent', function (dataLogin, callback) {
        console.log("NotReadyEvent message from server");

	    app.post('/notReadyResponseEvent', function(req, res){
	    	var stat = req.body.state;
	    	//send
	    	socket.emit('notReadyResponseEvent',function(stat){
	        	console.log('notReadyResponseEvent Emitted from server ');
	        })
	        res.send('Response received');
	    });
	});
});