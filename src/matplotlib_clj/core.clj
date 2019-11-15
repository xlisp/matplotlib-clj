(ns matplotlib-clj.core
  (:require [libpython-clj.python :as py]
            [tech.v2.datatype :as dtype]
            [tech.libs.buffered-image :as bufimg]
            [tech.v2.tensor :as dtt]
            [clojure.java.io :as io]
            [clojure.java.shell :as sh]))

(defn init-macox []
  (alter-var-root
   #'libpython-clj.jna.base/*python-library*
   (constantly "/usr/local/Cellar/python/3.7.1/Frameworks/Python.framework/Versions/3.7/lib/libpython3.7m.dylib"))
  (py/initialize!))

(init-macox)

(def mfig (py/import-module "matplotlib.figure"))
(def magg (py/import-module "matplotlib.backends.backend_agg"))
(def np (py/import-module "numpy"))
(def plt (py/import-module "matplotlib.pyplot"))

(def x (py/call-attr np "linspace" 0 2 100))
;;(py/python-type x)
;;(py/get-attr x "shape")

(defn plot-it []
  (py/call-attr-kw plt "plot" [x x] {"label" "linear"})
  (py/call-attr-kw plt "plot" [x (py/call-attr x "__pow__" 2)] {"label" "quadratic"})
  (py/call-attr-kw plt "plot" [x (py/call-attr x "__pow__" 3)] {"label" "cubic"})
  (py/call-attr plt "xlabel" "x label")
  (py/call-attr plt "ylabel" "y label")
  (py/call-attr plt "title" "Simple Plot")
  (py/call-attr plt "legend"))
