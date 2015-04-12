(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I am a little teapot !"))

(defn train []
(println "Choo Choo !"))
(println "Cleanliness is next to godliness")

(+ 1 (* 2 3) 4)

(if true
  (do (println "abraca dabra")
      "success" )
  (do (println "Hocus Pocus")
      "failure"))

(when (> 2 1)
  (println "2 is greater than 1")
  "greater")

(def failed-protoganists
  ["Larry Potter" 
   "The Incredible Bulk"])

(def asym-hobbit-body-parts 
  [{:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mount" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}])

(defn needs-matching-part?
  [part]
  (re-find #"left-" (:name part)))

(defn make-matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"left-" "right-")
   :size (:size part)})

(defn symmetrize-body-parts
  [asym-body-parts]
  (loop [remaining-asym-body-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-body-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-body-parts
            final-body-parts (conj final-body-parts part)]
        (if (needs-matching-part? part)
          (recur remaining (conj final-body-parts (make-matching-part part)))
          (recur remaining final-body-parts)))
     )))

(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
            (let [final-body-parts (conj final-body-parts part)]
              (if (needs-matchin-part? part)
                (conj final-body-parts (make-matching-part part))
                final-body-parts)))
          []
          asym-body-parts))

(defn hit
  [asym-body-parts]
  (let  [sym-parts (better-symmetrize-body-parts asym-body-parts)
         body-part-size-sum (reduce + 0 (map :size sym-parts))
         target (inc (rand body-part-size-sum))]
    (loop [[part & rest] sym-parts
           accumulated-size (:size part)]
      (if (> accumulated-size target)
        part
        (recur rest (+ accumulated-size (:size part))))))
)
