(ns financy.db)

(def transaction-records
  (atom []))

(defn transactions
  []
  @transaction-records)

(defn create-balance
  [transaction]
  (let [collections-records (swap! transaction-records conj transaction)]
    (merge transaction {:id (count collections-records)})))

(defn clear-database
 []
 (reset! transaction-records []))