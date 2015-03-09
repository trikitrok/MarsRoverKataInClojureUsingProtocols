(ns mars-rover.rover)

(defn rover [x y direction]
  {:x x :y y :direction direction})

(defprotocol Rotable
  (rotate-left [this])
  (rotate-right [this]))

(defprotocol Movable
  (move-forwards [this])
  (move-backwards [this]))

(defrecord FacingNorth [x y]
  Rotable
  (rotate-left 
    [{:keys [x y]}] 
    (rover x y :west))
  (rotate-right 
    [{:keys [x y]}] 
    (rover x y :east))
  
  Movable
  (move-forwards 
    [{:keys [x y]}]
    (rover x (inc y) :north))
  (move-backwards 
    [{:keys [x y]}]
    (rover x (dec y) :north)))

(defrecord FacingSouth [x y]
  Rotable
  (rotate-left 
    [{:keys [x y]}] 
    (rover x y :east))
  (rotate-right 
    [{:keys [x y]}] 
    (rover x y :west))
  
  Movable
  (move-forwards 
    [{:keys [x y]}]
    (rover x (dec y) :south))
  (move-backwards 
    [{:keys [x y]}]
    (rover x (inc y) :south)))

(defrecord FacingEast [x y]
  Rotable
  (rotate-left 
    [{:keys [x y]}] 
    (rover x y :north))
  (rotate-right 
    [{:keys [x y]}] 
    (rover x y :south))
  
  Movable
  (move-forwards 
    [{:keys [x y]}]
    (rover (inc x) y :east))
  (move-backwards 
    [{:keys [x y]}]
    (rover (dec x) y :east)))

(defrecord FacingWest [x y]
  Rotable
  (rotate-left 
    [{:keys [x y]}] 
    (rover x y :south))
  (rotate-right 
    [{:keys [x y]}] 
    (rover x y :north))
  
  Movable
  (move-forwards 
    [{:keys [x y]}]
    (rover (dec x) y :west))
  (move-backwards 
    [{:keys [x y]}]
    (rover (inc x) y :west)))

(defn oriented-rover [{:keys [x y direction]}]
  (case direction
    :north (FacingNorth. x y) 
    :south (FacingSouth. x y) 
    :east (FacingEast. x y) 
    :west (FacingWest. x y)))

(defn rotate-left [rover]
  (rotate-left (oriented-rover rover)))

(defn rotate-right [rover]
  (rotate-right (oriented-rover rover)))

(defn move-forwards [rover]
  (move-forwards (oriented-rover rover)))

(defn move-backwards [rover]
  (move-backwards (oriented-rover rover)))