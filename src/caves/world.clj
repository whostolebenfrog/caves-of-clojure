(ns caves.world)

(def world-size [160 50])

(defrecord World [tiles])
(defrecord Tile [kind glyph colour])

(def tiles
  {:floor (new Tile :floor "." :white)
   :wall  (new Tile :wall  "#" :white)
   :bound (new Tile :bound "X" :black)})

(defn get-tile [tiles x y]
  (get-in tiles [y x] (:bound tiles)))

;; todo - lets do this with anonymous functions instead.
;; not totally sold that multiple funs inside letfn is
;; very readable
(defn random-tiles []
  (let [[cols rows] world-size]
    (letfn [(random-tile []
              (tiles (rand-nth [:floor :wall])))
            (random-row []
              (vec (repeatedly cols random-tile)))]
      (vec (repeatedly rows random-row)))))

(defn random-world []
  (new World (random-tiles)))
