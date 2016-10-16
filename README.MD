## Requierements
Docker 1.12
Craftbukkit 1.10.2
SpigotMc 1.10.2

## Installation
Need to install docker https://www.docker.com/

Linux - In terminal 
```
sh create-images.sh
docker run -v $PWD/src:/src -ti viewers-are-gods:master run
```
Get your plugin compiled in src/target/anthonykgross-0.0.1-SNAPSHOT.jar

## Configuration 
src/src/main/resources/config.yml
```
irc:
  channel: "#anthonykgross" # change with your channel name
  port: 6667
  url: "irc.twitch.tv"
  login: "anthonykgross" # your login to connect to IRC
  pass: "" # Your password to connect to IRC. Details : https://help.twitch.tv/customer/portal/articles/1302780-twitch-irc

config:
  nb_polls_to_summon: 5 # How many poll to do before sending monster
  show_caution_at_percent: 50 # Show caution when the poll for one monster is higher than 50%
  message_summon: "Pokemon go! %entity% attacks !"
  message_caution: "%entity% (%percent%%)"
  player_to_focus: "anthonykgross" # Player to focus which will get the monster
```
Need to compile and update your server with the new plugin compiled

## Creator
**Anthony K GROSS**
- <http://anthonykgross.fr>
- <https://twitter.com/anthonykgross>
- <https://github.com/anthonykgross>

## Contributors

**JustOneGamerzz** 

**F1redev** 

**Hugo4715**

**LoockysTwtch**

## Copyright and license
Code and documentation copyright 2016. Code released under [the MIT license](https://github.com/anthonykgross/viewers-are-gods/blob/master/LICENSE).
