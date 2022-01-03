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

(defn- withdrawal? 
  [transaction]
  (= (:type transaction) "withdrawal"))

(defn- calculate 
  [acc transaction]
  (let [value (:value transaction)]
    (if (withdrawal? transaction)
      (- acc value)
      (+ acc value))))

(defn balance
  []
  (reduce calculate 0 @transaction-records))