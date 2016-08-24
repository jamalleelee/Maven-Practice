/*
 * Copyright (c) 2008, 2011 Oracle and/or its affiliates.
 * All rights reserved. Use is subject to license terms.
 *
 * This file is available and licensed under the following license:
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  - Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *  - Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the distribution.
 *  - Neither the name of Oracle Corporation nor the names of its
 *    contributors may be used to endorse or promote products derived
 *    from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.projectfive

import java.lang.Math.random
import javafx.animation.Animation
import javafx.animation.KeyFrame
import javafx.animation.KeyValue
import javafx.animation.Timeline
import javafx.application.Application
import javafx.beans.value.WritableValue
import javafx.scene.Group
import javafx.scene.Scene
import javafx.scene.effect.BlendMode
import javafx.scene.effect.BoxBlur
import javafx.scene.paint.Color._
import javafx.scene.paint.CycleMethod
import javafx.scene.paint.LinearGradient
import javafx.scene.paint.Stop
import javafx.scene.shape.Circle
import javafx.scene.shape.Rectangle
import javafx.scene.shape.StrokeType
import javafx.stage.Stage
import javafx.util.Duration
import scala.collection.JavaConversions._

/**
 * @author Oracle (original Java code)
 * @author Christian Schlichtherle (translated Scala code)
 */
class ColorfulCirclesDocs extends Application {
  override def start(stage: Stage) {
    // create scene
    val scene = new Scene(new Group, 800, 600, BLACK)
    stage.setScene(scene)
    // create first list of circles and set a blur effect
    val layer1 = new Group(Array.fill(15) {
        new Circle(200, web("white", .05)) {
          setStrokeType(StrokeType.OUTSIDE)
          setStroke(web("white", .2))
          setStrokeWidth(4f)
        }
      }: _*)
    layer1.setEffect(new BoxBlur(30, 30, 3))
    // create second list of circles and set a blur effect
    val layer2 = new Group(Array.fill(20) {
        new Circle(70, web("white", .05)) {
          setStrokeType(StrokeType.OUTSIDE)
          setStroke(web("white", .1))
          setStrokeWidth(2f)
        }
      }: _*)
    layer2.setEffect(new BoxBlur(2, 2, 2))
    // create third list of circles and set a blur effect
    val layer3 = new Group(Array.fill(10) {
        new Circle(150, web("white", .05)) {
          setStrokeType(StrokeType.OUTSIDE)
          setStroke(web("white", .16))
          setStrokeWidth(4f)
        }
      }: _*)
    layer3.setEffect(new BoxBlur(10, 10, 3))
    // create a rectangle size of window with colored gradient
    val colors = new Rectangle(
      scene.getWidth, scene.getHeight,
      new LinearGradient(0, 1, 1, 0, true, CycleMethod.NO_CYCLE,
                         new Stop(  0, web("#f8bd55")),
                         new Stop(.14, web("#c0fe56")),
                         new Stop(.28, web("#5dfbc1")),
                         new Stop(.43, web("#64c2f8")),
                         new Stop(.57, web("#be4af7")),
                         new Stop(.71, web("#ed5fc2")),
                         new Stop(.85, web("#ef504c")),
                         new Stop(  1, web("#f2660f")))
    )
    colors.setBlendMode(BlendMode.OVERLAY)
    // create main content
    val outerCircleGroup = new Group(
      new Group(new Rectangle(scene.getWidth, scene.getHeight, BLACK),
                layer1, layer2, layer3),
      colors
    )
    scene.getRoot.asInstanceOf[Group].getChildren.add(outerCircleGroup)
    // show stage
    stage.show()

    // Create a animation to randomly move every circle in allCircles
    val timeline = new Timeline
    for (circle <- List.concat(layer1.getChildren,
                               layer2.getChildren,
                               layer3.getChildren))
      timeline.getKeyFrames.addAll(
        new KeyFrame(
          Duration.ZERO, // set start position at 0s
          new KeyValue(
            circle.translateXProperty.asInstanceOf[WritableValue[Any]],
            random() * 800),
          new KeyValue(
            circle.translateYProperty.asInstanceOf[WritableValue[Any]],
            random() * 600)
        ),
        new KeyFrame(
          new Duration(40000), // set end position at 40s
          new KeyValue(
            circle.translateXProperty.asInstanceOf[WritableValue[Any]],
            random() * 800),
          new KeyValue(
            circle.translateYProperty.asInstanceOf[WritableValue[Any]],
            random() * 600)
        )
      )
    timeline.setAutoReverse(true)
    timeline.setCycleCount(Animation.INDEFINITE)
    // play 40s of animation
    timeline.play()
  }
}

object ColorfulCirclesDocs {
  def main(args: Array[String]) {
    Application.launch(classOf[ColorfulCirclesDocs], args)
  }
}
