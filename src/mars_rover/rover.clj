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
    [this] 
    (rover (:x this) (:y this) :west))
  (rotate-right 
    [this] 
    (rover (:x this) (:y this) :east))
  
  Movable
  (move-forwards 
    [this]
    (rover (:x this) (inc (:y this)) :north))
  (move-backwards 
    [this]
    (rover (:x this) (dec (:y this)) :north)))

(defrecord FacingSouth [x y]
  Rotable
  (rotate-left 
    [this] 
    (rover (:x this) (:y this) :east))
  (rotate-right 
    [this] 
    (rover (:x this) (:y this) :west))
  
  Movable
  (move-forwards 
    [this]
    (rover (:x this) (dec (:y this)) :south))
  (move-backwards 
    [this]
    (rover (:x this) (inc (:y this)) :south)))

(defrecord FacingEast [x y]
  Rotable
  (rotate-left 
    [this] 
    (rover (:x this) (:y this) :north))
  (rotate-right 
    [this] 
    (rover (:x this) (:y this) :south))
  
  Movable
  (move-forwards 
    [this]
    (rover (inc (:x this)) (:y this) :east))
  (move-backwards 
    [this]
    (rover (dec (:x this)) (:y this) :east)))

(defrecord FacingWest [x y]
  Rotable
  (rotate-left 
    [this] 
    (rover (:x this) (:y this) :south))
  (rotate-right 
    [this] 
    (rover (:x this) (:y this) :north))
  
  Movable
  (move-forwards 
    [this]
    (rover (dec (:x this)) (:y this) :west))
  (move-backwards 
    [this]
    (rover (inc (:x this)) (:y this) :west)))

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