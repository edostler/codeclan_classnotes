require('pg')
require_relative('../db/sql_runner')

class Customer

  attr_reader :id, :name

  def initialize(options)
    @id = options['id'].to_i
    @name = options['name']
  end

  def save()
    sql = "INSERT INTO customers (name) VALUES ($1) RETURNING id"
    values = [@name]
    @id = SqlRunner.run(sql, values)[0]['id'].to_i
  end

  def self.delete_all()
    sql = " DELETE FROM customers"
    SqlRunner.run(sql)
  end

  def self.all()
    sql = "SELECT * FROM customers"
    customers = SqlRunner.run(sql)
    return customers.map{ |person| Customer.new(person)}
  end

  def pizza_orders()
    sql = "SELECT * FROM pizza_orders WHERE customer_id = $1"
    values = [@id]
    orders = SqlRunner.run(sql, values)
    return orders.map{|order| PizzaOrder.new(order)}
  end







end
