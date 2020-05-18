# Dji tello drone desktop controller

This project is only an test for me to integrate drone DJI TELLO witch you sdk base [here][1]

The data received, commands and UI are maked of me, but the video input is based of code by [this project][2]

Image of software:

![Software image][4]

![Software image][3]

This software is multilanguage, **English and portuguese**

## How use

Clone this repository, Connect to wifi of the Tello Drone and run project wich your IDE, if all working fine, the numbers on the top of ui will update on drone moviment

- Controls
  - A : Left
  - D : Right
  - W : Forward
  - S : Backward
  - E : Rotate Right
  - Q : Rotate Left
  - O : Flight up
  - L : Land

You can change drone velocity on `TelloConstants` class

The program have a manual input on console of IDE of you want to send [custom commands of sdk][1] (Dont work witch answer commands that end witch ?)

[1]: https://terra-1-g.djicdn.com/2d4dce68897a46b19fc717f3576b7c6a/Tello%20%E7%BC%96%E7%A8%8B%E7%9B%B8%E5%85%B3/For%20Tello/Tello%20SDK%20Documentation%20EN_1.3_1122.pdf
[2]: https://github.com/FriwiDev/Tello4J
[3]: https://image.prntscr.com/image/FqoEvm5JQf6yQQ2F4HUFwA.png
[4]: https://image.prntscr.com/image/vpQguXeOS9iGfK_ZjfjpnQ.png
