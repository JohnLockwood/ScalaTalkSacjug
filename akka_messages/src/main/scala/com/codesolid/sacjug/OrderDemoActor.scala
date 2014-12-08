package com.codesolid.sacjug

import akka.actor.{ActorSystem, Props, Actor}
import akka.actor.Actor.Receive
import akka.event.Logging

import scala.util.Random

/**
 * Created by john on 11/15/14.
 * This is less of a test than it is a demonstration.  printf is synchronous and shows
 * that for a single actor instance, calls are queued in order.  log.debug on the other hand
 * is asynchronous.
 */
class OrderDemoActor extends Actor {
    val log = Logging(context.system, this)
    val random = new Random()

    override def receive: Receive = {
        case num : Int => processInt(num)
    }

    def processInt(num: Int): Unit = {

        printf("%2d begin\n", num)
        log.debug(num.toString())
        val upperBound = random.nextInt(200)
            for (x <- 0 to upperBound) {
                var y = x * 2
            }
        printf("%2d end\n", num) // log.debug(num.toString)
    }
}

object OrderDemoActorTestApp extends App {
    println("Beginning demo of orderint of messages in OrderDemoActor")
    val system = ActorSystem("demo")

    val orderDemoActor = system.actorOf(Props[OrderDemoActor], name = "orderDemoActor")

    for (n <- 1 to 200) {
        orderDemoActor ! n
    }

    // Wait for main thread to catch up
    Thread.sleep(3000)
}
