(ns financy.balance-test
  (:require [midje.sweet :refer [fact against-background]]
            [server-helper :refer [start-server stop-server content default-port]]
            [midje.parsing.arrow-symbols :refer [=>]]
            [midje.parsing.1-to-explicit-form.parse-background :refer [before after]]))

(fact "start and stop server" :acceptance
      (start-server 3001)
      (stop-server))

(against-background
 [(before :facts (start-server default-port)) (after :facts (stop-server))]
 (fact "initial balance must be equal 0" :acceptance
       (content "/balance") => "0"
       (stop-server)))
