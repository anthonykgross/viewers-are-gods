## Installation

## Configuration 
src/src/main/resources/config.yml
```
irc:
  channel: "#anthonykgross" # chqnge with your channel name
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
