(ns bank-cards-poc.core
  (:require
    [ring.adapter.jetty :refer [run-jetty]]
    [bank-cards-poc.routes :refer [app]])
  (:gen-class))

(defn -main [& _]
  (println "Servidor iniciado em http://localhost:3000")
  (run-jetty app {:port 3000}))
