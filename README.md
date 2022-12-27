# Tanks

Protocol concept
1. Name of protocol tnkp. Now it has version 1.0
2. It has 3 types of messages: CONNECT, GET ALL, POST ALL, and server Responses with particular status codes
3. When user connects he sends CONNECT request to the server then he joins map
4. Every certain period of time client sends GET ALL and POST ALL requests
5. Server receives GET ALL and answers with response which contains all coordinates of enemy player
6. Server receives POST ALL and writes actual data of player who sent this requset to the storage
7. Client receives Server's response and render enemy tank by the received data
8. Empty line is an end of a message

Typical examples of requests
1. GET ALL tnkp/1.0
<br>{empty line}
2. POST ALL tnkp/1.0
<br>TANK_COORD_X:18.9
<br>TANK_COORD_Y:56.4
<br>TANK_ANGLE:18.6
<br>TURRET_ANGLE:56.1
<br>BULLET_DIRECTION_X:67.2
<br>BULLET_DIRECTION_Y:57.4
<br>{empty line}

Typical server response
1. 200 tnkp/1.0
<br>TANK_COORD_X:18.9
<br>TANK_COORD_Y:56.4
<br>TANK_ANGLE:18.6
<br>TURRET_ANGLE:56.1
<br>BULLET_DIRECTION_X:67.2
<br>BULLET_DIRECTION_Y:57.4
<br>{empty line}

### <br /> Authors of the project
***
***Gilmutdinov Timerbulat<br/>***
***timerhelmut@gmail.com<br/>***
***Telegram: @bulatinogg***
***
***Gafiatullin Aidar<br/>***
***Telegram: Ai_7790***
