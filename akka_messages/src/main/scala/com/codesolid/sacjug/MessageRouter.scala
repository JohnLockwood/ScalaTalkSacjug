package com.codesolid.sacjug

import akka.actor.Actor
//import akka.actor.ActorSystem
//import akka.actor.Props

/**
 * Created by john on 11/10/14.
 */
class MessageRouter extends Actor {
    def receive = {
        case _       => println("No lo entiendo!")
    }
}
