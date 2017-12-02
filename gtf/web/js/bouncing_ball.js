//*********** BALL

	//* This is for hidden ball..... haha...
	/*
	[*] SCRIPT: Bouncing Image Script COPYRIGHT: c 1999 Hass
	[*] AUTHOR: Lloyd Hassell EMAIL: lloydhass@hotmail.com
	[*] WWW: http://go.to/hass
	[*] Permission granted to Dynamicdrive.com to feature script in archive
	[*] Modified by Dynamicdrive.com for feature alteration
	[*] For this and 100's more DHTML scripts, visit http://dynamicdrive.com
	*/

	//Configure the below three variables (BallSpeed from 1 to 50, larger is faster)
	document.BallSpeed = 3;
	document.BallState = 0;

	var ballWidth = 40;
	var ballHeight = 40;
	var maxBallSpeed = 50;
	var xMax;
	var yMax;
	var xPos = 0;
	var yPos = 0;
	var xDir = 'right';
	var yDir = 'down';
	var superballRunning = true;
	var tempBallSpeed;
	var currentBallSrc;
	var newXDir;
	var newYDir;
	var ballTimer;


	function initializeBall()
    {
	   if (document.all) {
	      xMax = document.body.clientWidth
	      yMax = document.body.clientHeight
	      document.all("superball").style.visibility = "visible";
	   }
	   else if (document.layers) {
	      xMax = window.innerWidth;
	      yMax = window.innerHeight;
	      document.layers["superball"].visibility = "show";
	   }
	   ballTimer = setTimeout('moveBall()',400);
	}

	function hideBall()
    {
	   if (document.all) {
	      xMax = document.body.clientWidth
	      yMax = document.body.clientHeight
	      document.all("superball").style.visibility = "hidden";
	   }
	   else if (document.layers) {
	      xMax = window.innerWidth;
	      yMax = window.innerHeight;
	      document.layers["superball"].visibility = "hide";
	   }
	   clearTimeout(ballTimer);
	}

	function moveBall()
    {
	   if (superballRunning == true) {
	      calculatePosition();
	      if (document.all) {
	         document.all("superball").style.left = xPos + document.body.scrollLeft;
	         document.all("superball").style.top = yPos + document.body.scrollTop;
	      }
	      else if (document.layers) {
	         document.layers["superball"].left = xPos + pageXOffset;
	         document.layers["superball"].top = yPos + pageYOffset;
	      }
	      ballTimer = setTimeout('moveBall()',30);
	   }
	}

	function calculatePosition()
    {
	   if (xDir == "right") {
	      if (xPos > (xMax - ballWidth - document.BallSpeed)) {
	         xDir = "left";
	      }
	   }
	   else if (xDir == "left") {
	      if (xPos < (0 + document.BallSpeed)) {
	         xDir = "right";
	      }
	   }

	   if (yDir == "down") {
	      if (yPos > (yMax - ballHeight - document.BallSpeed)) {
	         yDir = "up";
          }
      }
	   else if (yDir == "up") {
	      if (yPos < (0 + document.BallSpeed)) {
	         yDir = "down";
         }
      }

	   if (xDir == "right") {
	      xPos = xPos + document.BallSpeed;
      }
	   else if (xDir == "left") {
	      xPos = xPos - document.BallSpeed;
      }
	   else {
	      xPos = xPos;
      }

	   if (yDir == "down") {
	      yPos = yPos + document.BallSpeed;
	   }
	   else if (yDir == "up") {
	      yPos = yPos - document.BallSpeed;
      }
	   else {
	      yPos = yPos;
	      }
	   }

	function playBall()
    {
		initializeBall();
		window.onresize = new Function("window.location.reload()");
	}

