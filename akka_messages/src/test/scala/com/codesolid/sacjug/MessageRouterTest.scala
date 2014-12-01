package com.codesolid.sacjug

import akka.actor
import akka.actor.{Kill, UnhandledMessage, Props, ActorSystem}
import akka.testkit.{EventFilter, TestKit, ImplicitSender, TestActorRef}
import com.typesafe.config.ConfigFactory
import org.scalatest.{fixture, Matchers, BeforeAndAfterAll, WordSpecLike}


/**
 * Created by john on 11/14/14.
 */

    class MessageRouterTest(_system: ActorSystem)  extends BaseTestKit(_system) {

    def this() = this(ActorSystem("MessageRouterTest"))

    override def afterAll {
        TestKit.shutdownActorSystem(system)
    }

    "A message router" must {

        "respond like a third grader if sent a \"Hey\"" in {
            val router = system.actorOf(Props[MessageRouterActor])
            router ! "Hey"
            expectMsg("Hay is for horses")
        }

        "send \"Unknown message type\" if sent an unhandled type" in {
            val router = system.actorOf(Props[MessageRouterActor])

            router ! 42
            expectMsg("Unknown message type")

            // To do - haven't figured this out yet
            // expectMsgClass(classOf[UnhandledMessage])

        }

        "send \"Exam Message\" if sent an Exam Message" in {
            val router = system.actorOf(Props[MessageRouterActor])

            val emptyMap : Map[String,String] = Map()
            val m = ExamMessage(emptyMap, "Patient is looking a bit pale, and is missing his nose.")
            router ! m
            expectMsg("Exam Message")
        }

        "express sympathy if sent an Exam Message for dead patient" in {
            val router = system.actorOf(Props[MessageRouterActor])

            val emptyMap : Map[String,String] = Map()
            val m = ExamMessage(emptyMap, "dead")
            router ! m
            expectMsg("We're very sorry for your loss")
        }

        "send \"Schedule Message\" plus time if sent a Schedule Message" in {
            val router = system.actorOf(Props[MessageRouterActor])
            val emptyMap : Map[String,String] = Map()
            val m = ScheduleMessage(emptyMap, "12 PM")
            router ! m
            expectMsg("Schedule Message 12 PM")
        }
    }


}
