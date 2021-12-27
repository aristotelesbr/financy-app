(ns financy.balance-test
  (:require [midje.sweet :refer [fact]]
            [financy.handler :refer [app]]
            [ring.adapter.jetty :refer [run-jetty]]
            [clj-http.client :as http]
            [midje.parsing.arrow-symbols :refer [=>]]))

(def state-server (atom nil))

(defn start-server
  [port]
  (swap! state-server
         (fn [_] (run-jetty app {:port port :join? false}))))

(defn stop-server
  []
  (.stop @state-server))

(fact "start and stop server" :acceptance
      (start-server 3001)
      (stop-server))

(fact "initial balance must be equal 0" :acceptance
      (start-server 3001)
      (:body (http/get "http://localhost:3001/balance")) => "0"
      (stop-server))
