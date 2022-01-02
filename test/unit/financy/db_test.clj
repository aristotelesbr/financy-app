(ns financy.db-test
  (:require [midje.sweet :refer [facts fact against-background]]
            [midje.parsing.arrow-symbols :refer [=>]]
            [financy.db :refer [create-balance transactions clear-database]]
            [midje.parsing.1-to-explicit-form.parse-background :refer [before]]))

(facts "save transaction in atom"
       (against-background [(before :facts (clear-database))]
                           (fact "initial transactions list must be empty"
                                 (count (transactions)) => 0)
                           (fact "transaction is first a record"
                                 (create-balance {:value 7 :type "input"}) => {:id 1 :value 7 :type "input"})))



