var Bank = function() {
  this.accounts = [];
}

Bank.prototype.addAccount = function (account) {
  this.accounts.push(account);
}


Bank.prototype.accountByName = function(name){
  var foundAccount = this.accounts.find(function(account){
    if(account.name === name){
      return account;
    }
  });
  return foundAccount;
}

Bank.prototype.largestAccount = function(){
  var largestAccount = this.accounts[0];
  this.accounts.forEach(function(account){
    if(account.value > largestAccount.value){
      largestAccount = account;
    }
  });
  return largestAccount;
}


Bank.prototype.payInterest = function(){
  this.accounts.forEach(function(account){
    account.value *= 1.1;
  })
  return this.accounts;
}

Bank.prototype.businessAccounts = function(){
  var businessAccounts = [];
    this.accounts.forEach(function(account){
      if(account.type === "business"){
        businessAccounts.push(account);
      }
    })
    return businessAccounts;
}


Bank.prototype.totalValue = function(){
  var total = 0;
  this.accounts.forEach(function(account){
    total += account.value;
  });
  return total;
}

Bank.prototype.averageValue = function(){
  var averageValue = 0;
  var totalValue = this.totalValue();
  averageValue = totalValue/this.accounts.length;
  return averageValue;
}


module.exports = Bank;
