(ns fwpd.core
  (:require [clojure.string :as s]))

(def filename "suspects.csv")

(def header->keywords {"Name" :name
                       "Glitter Index" :glitter-index})

(defn str->int 
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn parse
  "converts a csv into rows and cols"
  [string]
  (map #(s/split % #",") 
       (s/split string #"\n")))

(defn mapify
  "Returns a seq of maps "
  [rows]
  (let [ headers (map #(get header->keywords %) (first rows))
        unmapped-rows (rest rows)]
    (map (fn [unmapped-row] 
           (into {}
                 (map (fn [header column]
                        [header ((get conversions header) column)])
                      headers
                      unmapped-row)))
         unmapped-rows)))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

