(ns financy.db-test
  (:require [midje.sweet :refer [facts fact against-background]]
            [midje.parsing.arrow-symbols :refer [=>]]
            [financy.db :refer [create-balance transactions clear-database balance]]
            [midje.parsing.1-to-explicit-form.parse-background :refer [before]]))

(facts "save transaction in atom"
       (against-background [(before :facts (clear-database))]
                           (fact "initial transactions list must be empty"
                                 (count (transactions)) => 0)
                           (fact "transaction is first a record"
                                 (create-balance {:value 7 :type "input"}) => {:id 1 :value 7 :type "input"})))

(facts "calculate balance form collection of transactions"
       (against-background [(before :facts (clear-database))]
                           (fact "balance is positive when have only input transacts"
                                 (create-balance {:value 2 :type "input"})
                                 (create-balance {:value 10 :type "input"})
                                 (balance) => 12)
                           (fact "balance is negative when have only withdrawal"
                                 (create-balance {:value 3 :type "withdrawal"})
                                 (create-balance {:value 9 :type "withdrawal"})
                                 (balance) => -12)
                           (fact "balance is equal sum of input - sum of withdrawal"
                                 (create-balance {:value 100 :type "input"})
                                 (create-balance {:value 20 :type "withdrawal"})
                                 (balance) => 80)))

