(ns mars-rover.rover)

(defn rover [x y direction]
  {:x x :y y :direction direction})

(defprotocol Rotable
  (left [this])
  (right [this]))

(defprotocol Movable
  (forwards [this])
  (backwards [this]))

(defrecord FacingNorth [x y]
  Rotable
  (left 
    [this] 
    (rover (:x this) (:y this) :west))
  (right 
    [this] 
    (rover (:x this) (:y this) :east))
  
  Movable
  (forwards 
    [this]
    (rover (:x this) (inc (:y this)) :north))
  (backwards 
    [this]
    (rover (:x this) (dec (:y this)) :north)))


(defrecord FacingSouth [x y]
  Rotable
  (left 
    [this] 
    (rover (:x this) (:y this) :east))
  (right 
    [this] 
    (rover (:x this) (:y this) :west))
  
  Movable
  (forwards 
    [this]
    (rover (:x this) (dec (:y this)) :south))
  (backwards 
    [this]
    (rover (:x this) (inc (:y this)) :south)))


(defrecord FacingEast [x y]
  Rotable
  (left 
    [this] 
    (rover (:x this) (:y this) :north))
  (right 
    [this] 
    (rover (:x this) (:y this) :south))
  
  Movable
  (forwards 
    [this]
    (rover (inc (:x this)) (:y this) :east))
  (backwards 
    [this]
    (rover (dec (:x this)) (:y this) :east)))


(defrecord FacingWest [x y]
  Rotable
  (left 
    [this] 
    (rover (:x this) (:y this) :south))
  (right 
    [this] 
    (rover (:x this) (:y this) :north))
  
  Movable
  (forwards 
    [this]
    (rover (dec (:x this)) (:y this) :west))
  (backwards 
    [this]
    (rover (inc (:x this)) (:y this) :west)))

(defn oriented-rover [{:keys [x y direction]}]
  (case direction
    :north (FacingNorth. x y) 
    :south (FacingSouth. x y) 
    :east (FacingEast. x y) 
    :west (FacingWest. x y)))

(defn rotate-left [rover]
  (left (oriented-rover rover)))

(defn rotate-right [rover]
  (right (oriented-rover rover)))

(defn move-forwards [rover]
  (forwards (oriented-rover rover)))

(defn move-backwards [rover]
  (backwards (oriented-rover rover)))