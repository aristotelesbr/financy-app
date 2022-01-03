(ns financy.balance-test
  (:require [midje.sweet :refer [fact against-background]]
            [server-helper :refer [start-server stop-server content default-port url-for]]
            [midje.parsing.arrow-symbols :refer [=>]]
            [midje.parsing.1-to-explicit-form.parse-background :refer [before after]]
            [cheshire.core :as json]
            [clj-http.client :as http]
            [financy.db :as db]))

(fact "start and stop server" :acceptance
      (start-server 3001)
      (stop-server))

(against-background
 [(before :facts [(start-server default-port) (db/clear-database)]) (after :facts (stop-server))]
 (fact "initial balance must be 0" :acceptance
       (json/parse-string (content "/balance") true) => {:balance 0})

 (fact "balancy is 10 when a transaction is 10" :acceptance
       (http/post (url-for "/transactions")
                  {:content-type :json
                   :body (json/generate-string {:value 10 :type "input"})})
       (json/parse-string (content "/balance") true) => {:balance 10}))

