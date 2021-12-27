(ns financy.integration.balance-test
  (:require [midje.sweet :refer [fact facts]]
            [financy.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]))

(def server (atom nil))

(defn start
  [port]
  (swap! server
         (fn [_] (run-jetty app {:port port :join? false}))))

(defn stop
  []
  (.stop @server))

(fact "start and stop server"
      (start 3001)
      (stop))
