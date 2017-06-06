CREATE TABLE IF NOT EXISTS `tax` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `amount` DOUBLE NOT NULL,
  `percent` DOUBLE NOT NULL,
  `type_tax_use` VARCHAR(40) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`));
  
  CREATE TABLE IF NOT EXISTS `account` (

  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(256) NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `type` VARCHAR(64) NOT NULL,
  `code` VARCHAR(64) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`));
    

    
CREATE TABLE IF NOT EXISTS `product_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(40) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`));

  
    
CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(64) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  `name` VARCHAR(64) NOT NULL,
  `user_type` VARCHAR(64) NULL,
  `image` LONGBLOB NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`));


 CREATE TABLE IF NOT EXISTS `product_uom_category` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  
  PRIMARY KEY (`id`));


  CREATE TABLE IF NOT EXISTS `product_uom` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `decimals` INT NOT NULL,
  `category_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `product_uom_category_id_fkey` 
    FOREIGN KEY (`category_id`) 
    REFERENCES `product_uom_category`(`id`));



CREATE TABLE IF NOT EXISTS `product` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `image` LONGBLOB NULL,
  `default_code` VARCHAR(64) NULL,
  `name` VARCHAR(128) NOT NULL,
  `image_medium` LONGBLOB NULL,
  `sale_price` DOUBLE NULL DEFAULT 0, 
  `purchase_price` DOUBLE NULL DEFAULT 0,
  `description` VARCHAR(128) NULL,
  `weight` DOUBLE NULL,
  `volume` DOUBLE NULL,
  `Lenght` DOUBLE NULL,
  `uom_id` INT NOT NULL,
  `categ_id` INT NOT NULL,
  `sale_ok` TINYINT NULL,
  `purchase_ok` TINYINT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `product_template_categ_id_fkey`
    FOREIGN KEY (`categ_id`)
    REFERENCES `product_category` (`id`),
  CONSTRAINT `product_template_uom_id_fkey`
    FOREIGN KEY (`uom_id`)
    REFERENCES `product_uom` (`id`));

    
    
CREATE TABLE IF NOT EXISTS `partner` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `create_date` DATETIME NOT NULL,
  `name` VARCHAR(128) NOT NULL,
  `image` LONGBLOB NULL,
  `city` VARCHAR(128) NULL,
  `country` VARCHAR(128) NULL,
  `street` VARCHAR(128) NULL,
  `supplier` TINYINT NULL,
  `customer` TINYINT NULL,
  `email` VARCHAR(64) NULL,
  `website` VARCHAR(64) NULL,
  `fax` VARCHAR(64) NULL,
  `phone` VARCHAR(64) NULL,
  `credit` DOUBLE NULL,
  `debit` DOUBLE NULL,
  `image_medium` LONGBLOB NULL,
  `mobile` VARCHAR(64) NULL,
  `is_company` TINYINT NULL,
  `purchase_deals` INT NULL DEFAULT 0,
  `sale_deals` INT NULL DEFAULT 0,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  `accountReceivable_id` INT NOT NULL,
  `accountPayable_id` INT NOT NULL,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `partner_accountReceivable_id_fkey`
    FOREIGN KEY (`accountReceivable_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `partner_accountPayable_id_fkey`
    FOREIGN KEY (`accountPayable_id`)
    REFERENCES `account` (`id`));
    
    
CREATE TABLE IF NOT EXISTS `sale_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `partner_id` INT NOT NULL,
  `amount_tax` DOUBLE NULL,
  `amount_total` DOUBLE NULL,
  `amount_untaxed` DOUBLE NULL,
  `state` VARCHAR(64) NULL,
  `invoice_method` VARCHAR(64) NULL,
  `name` VARCHAR(64) NULL,
  `delivery_created` TINYINT NULL,
  `shipped` TINYINT NULL,
  `paid` TINYINT NULL,
  `unpaid` DOUBLE NULL,
  `notes` LONGTEXT NULL,
  `discount` INT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `sale_order_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`));
    
  
CREATE TABLE IF NOT EXISTS `sale_order_line` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `uom` VARCHAR(60) NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `order_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `sub_total` DOUBLE NOT NULL,
  `discount` DOUBLE NOT NULL,
  `tax_id` INT NULL,
  `name` VARCHAR(128) NULL,
  `product_id` INT  NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  `invoiced` TINYINT NOT NULL,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `sale_order_line_order_id_fkey`
    FOREIGN KEY (`order_id`)
    REFERENCES `sale_order` (`id`),
  CONSTRAINT `sale_order_line_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`),
  CONSTRAINT `sale_order_line_tax_id_fkey`
    FOREIGN KEY (`tax_id`)
    REFERENCES `tax` (`id`));

   
CREATE TABLE IF NOT EXISTS `journal` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  `code` VARCHAR(64) NOT NULL,
  `type` VARCHAR(64) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`));
   

    
CREATE TABLE IF NOT EXISTS `purchase_order` (

  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `partner_id` INT NOT NULL,
  `amount_tax` DOUBLE NULL,
  `amount_total` DOUBLE NULL,
  `amount_untaxed` DOUBLE NULL,
  `name` VARCHAR(64) NULL,
  `state` VARCHAR(64) NULL,
  `invoice_method` VARCHAR(64) NULL,
  `delivery_created` TINYINT NULL,
  `shipped` TINYINT NULL,
  `paid` TINYINT NULL,
  `unpaid` DOUBLE NULL,
  `notes` LONGTEXT NULL,
  `discount` INT NULL,
  `reference`  VARCHAR(64) NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `purchase_order_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`));
    
    
CREATE TABLE IF NOT EXISTS `journal_entry` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `journal_id` INT NULL,
  `name` VARCHAR(64) NULL,
  `ref` VARCHAR(64) NULL,
  `date` DATETIME NULL,
  `amount` DOUBLE NOT NULL,
  `state` VARCHAR(64) NULL,
  `partner_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `journal_entry_journal_id_fkey`
    FOREIGN KEY (`journal_id`)
    REFERENCES `journal` (`id`),
  CONSTRAINT `journal_entry_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`));    
 
 
CREATE TABLE IF NOT EXISTS `journal_item` (
 
  `id` INT NOT NULL AUTO_INCREMENT,
  `partner_id` INT NULL,
  `account_id` INT NULL,
  `debit` DOUBLE NOT NULL,
  `credit` DOUBLE NOT NULL,
  `cost_of_goods_sold` DOUBLE NOT NULL,
  `residual_amount` DOUBLE NULL,
  `date` DATETIME NOT NULL,
  `journal_id` INT NULL,
  `name` VARCHAR(64) NULL,
  `ref` VARCHAR(64) NULL,
  `entry_id` INT NULL,
  `product_id` INT NULL,
  `tax_amount` DOUBLE NULL,
  `quantity` DOUBLE NULL,
  `uom_id` INT NULL,
  `tax_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `journal_entry_line_tax_id_fkey`
    FOREIGN KEY (`tax_id`)
    REFERENCES `tax` (`id`),
  CONSTRAINT `journal_entry_line_account_id_fkey`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `journal_entry_line_journal_id_fkey`
    FOREIGN KEY (`journal_id`)
    REFERENCES `journal` (`id`),
  CONSTRAINT `journal_entry_line_entry_id_fkey`
    FOREIGN KEY (`entry_id`)
    REFERENCES `journal_entry` (`id`),
  CONSTRAINT `journal_entry_line_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`),
  CONSTRAINT `journal_entry_line_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`),
  CONSTRAINT `journal_entry_line_product_uom_id_fkey`
    FOREIGN KEY (`uom_id`)
    REFERENCES `product_uom` (`id`));


CREATE TABLE IF NOT EXISTS `inventory` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `max_qty` DOUBLE NULL,
  `min_qty` DOUBLE NULL,
  `quantity` DOUBLE NOT NULL,
  `incoming` DOUBLE NOT NULL,
  `reserved` DOUBLE NOT NULL,
  `unit_cost` DOUBLE NOT NULL,
  `total_cost` DOUBLE NOT NULL,
  `product_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `inventory_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`));  
 
    
CREATE TABLE IF NOT EXISTS `invoice` (

  `id` INT NOT NULL AUTO_INCREMENT,
  `sale_id` INT NULL,
  `purchase_id` INT NULL,
  `date` DATETIME NOT NULL,
  `account_id` INT NULL,
  `journal_id` INT NULL,
  `entry_id` INT NULL,
  `partner_id` INT NOT NULL,
  `name` VARCHAR(64) NULL,
  `amount_untaxed` DOUBLE NULL,
  `amount_total` DOUBLE NULL,
  `amount_tax` DOUBLE NULL,
  `type` VARCHAR(64) NULL,
  `origin` VARCHAR(64) NULL,
  `reference` VARCHAR(64) NULL,
  `comment` LONGTEXT NULL,
  `residual` DOUBLE NULL, 
  `state` VARCHAR(64) NULL,
  `number` VARCHAR(32) NULL,
  `supplier_invoice_number` VARCHAR(64) NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
    
  PRIMARY KEY (`id`),
  CONSTRAINT `invoice_sale_id_fkey`
    FOREIGN KEY (`sale_id`)
    REFERENCES `sale_order` (`id`),
  CONSTRAINT `invoice_purchase_id_fkey`
    FOREIGN KEY (`purchase_id`)
    REFERENCES `purchase_order` (`id`),
  CONSTRAINT `invoice_account_id_fkey`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `invoice_journal_id_fkey`
    FOREIGN KEY (`journal_id`)
    REFERENCES `journal` (`id`),
  CONSTRAINT `invoice_entry_id_fkey`
    FOREIGN KEY (`entry_id`)
    REFERENCES `journal_entry` (`id`),
  CONSTRAINT `invoice_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`));
    

CREATE TABLE IF NOT EXISTS `payment`  (

  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NOT NULL,
  `partner_id` INT NULL,
  `amount` DOUBLE NOT NULL,
  `overpayment` DOUBLE NOT NULL,
  `name` VARCHAR(64) NULL,
  `journal_id` INT NULL,
  `entry_id` INT NULL,
  `account_id` INT NULL,
  `invoice_id` INT NULL, 
  `reference` VARCHAR(64) NULL,
  `type` VARCHAR(64) NULL,
  `partner_type` VARCHAR(64) NOT NULL,
  `state` VARCHAR(64) NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `payment_invoice_id_fkey`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`),
  CONSTRAINT `payment_account_id_fkey`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `payment_journal_id_fkey`
    FOREIGN KEY (`journal_id`)
    REFERENCES `journal` (`id`),
  CONSTRAINT `payment_entry_id_fkey`
    FOREIGN KEY (`entry_id`)
    REFERENCES `journal_entry` (`id`),
  CONSTRAINT `payment_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`));
  
  
CREATE TABLE IF NOT EXISTS `purchase_order_line` (
 
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `order_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `uom` VARCHAR(60) NOT NULL,
  `state` VARCHAR(64) NULL,
  `product_id` INT NOT NULL,
  `name` VARCHAR(64) NULL,
  `sub_total` DOUBLE NOT NULL,
  `tax_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  `invoiced` TINYINT NOT NULL,
  
  
  PRIMARY KEY (`id`),
  CONSTRAINT `purchase_order_line_tax_id_fkey`
    FOREIGN KEY (`tax_id`)
    REFERENCES `tax` (`id`),
  CONSTRAINT `purchase_order_line_order_id_fkey`
    FOREIGN KEY (`order_id`)
    REFERENCES `purchase_order` (`id`),
  CONSTRAINT `purchase_order_line_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`));
    
    
CREATE TABLE IF NOT EXISTS `invoice_line` (
    
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `uom` VARCHAR(60) NOT NULL,
  `account_id` INT NULL,
  `name` VARCHAR(64) NULL,
  `invoice_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `price_subtotal` DOUBLE NOT NULL,
  `tax_id` INT NULL,
  `discount` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `product_id` INT NULL,
  `partner_id` INT NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `invoice_line_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`),
  CONSTRAINT `invoice_line_account_id_fkey`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `invoice_line_invoice_id_fkey`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`),
  CONSTRAINT `invoice_line_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`),
  CONSTRAINT `invoice_line_tax_id_fkey`
    FOREIGN KEY (`tax_id`)
    REFERENCES `tax` (`id`));

      
CREATE TABLE IF NOT EXISTS `delivery_order` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `partner_id` INT NOT NULL,
  `back_order_id` INT NULL,
  `date` DATETIME NOT NULL,
  `sale_id` INT NULL,
  `purchase_id` INT NULL,
  `origin` VARCHAR(64) NULL,
  `name` VARCHAR(64) NULL,
  `state` VARCHAR(64) NULL,
  `delivery_method` VARCHAR(64)  NULL,
  `type` VARCHAR(64) NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
 



  PRIMARY KEY (`id`),
  CONSTRAINT `delivery_order_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`),
  CONSTRAINT `delivery_order_back_order_id_fkey`
    FOREIGN KEY (`back_order_id`)
    REFERENCES `delivery_order` (`id`),
  CONSTRAINT `delivery_order_purchase_id_fkey`
    FOREIGN KEY (`purchase_id`)
    REFERENCES `purchase_order` (`id`),
  CONSTRAINT `delivery_order_sale_id_fkey`
    FOREIGN KEY (`sale_id`)
    REFERENCES `sale_order` (`id`));



CREATE TABLE IF NOT EXISTS `delivery_order_line` (

  `id` INT NOT NULL AUTO_INCREMENT,
  `delivery_id` INT NOT NULL,
  `uom` VARCHAR(60) NOT NULL,
  `product_id` INT NOT NULL,
  `price` DOUBLE NOT NULL,
  `quantity` DOUBLE NOT NULL,
  `reserved` DOUBLE NOT NULL,  
  `partner_id` INT NULL,
  `type` VARCHAR(64) NULL,  
  `state` VARCHAR(64) NOT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
   
  PRIMARY KEY (`id`),
  CONSTRAINT `delivery_order_line_partner_id_fkey`
    FOREIGN KEY (`partner_id`)
    REFERENCES `partner` (`id`),
  CONSTRAINT `delivery_order_line_picking_id_fkey`
    FOREIGN KEY (`delivery_id`)
    REFERENCES `delivery_order` (`id`),
  CONSTRAINT `delivery_order_line_product_id_fkey`
    FOREIGN KEY (`product_id`)
    REFERENCES `product` (`id`));
    
    
 CREATE TABLE IF NOT EXISTS `invoice_tax` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `date` DATETIME NULL,
  `tax_amount` DOUBLE NULL,
  `account_id` INT NULL,
  `name` VARCHAR(64)  NULL,
  `invoice_id` INT NULL,
  `base_amount` DOUBLE NULL,
  `tax_id` INT NULL,
  `active` TINYINT NOT NULL DEFAULT TRUE,
  
  PRIMARY KEY (`id`),
  CONSTRAINT `account_invoice_tax_account_id_fkey`
    FOREIGN KEY (`account_id`)
    REFERENCES `account` (`id`),
  CONSTRAINT `account_invoice_tax_invoice_id_fkey`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`),
  CONSTRAINT `account_invoice_tax_tax_id_fkey`
    FOREIGN KEY (`tax_id`)
    REFERENCES `tax` (`id`));

  

CREATE TABLE IF NOT EXISTS `invoice_payment` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `invoice_id` INT NOT NULL,
  `journal_entry_id` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `name` VARCHAR(64)  NULL,
  `paid_amount` DOUBLE NOT NULL,

PRIMARY KEY (`id`),
CONSTRAINT `invoice_payment_journal_entry_id_fkey`
    FOREIGN KEY (`journal_entry_id`)
    REFERENCES `journal_entry` (`id`),
CONSTRAINT `invoice_payment_invoice_id_fkey`
    FOREIGN KEY (`invoice_id`)
    REFERENCES `invoice` (`id`));


INSERT INTO `account` (NAME, title, active,TYPE, CODE) 
VALUES  ('1234 Account Receivable','1234 Account Receivable',1,'Receivable','1234'),
        ('1234 Cash','Cash',1,'Bank and Cash','1234'),
        ('1234 Bank','Bank',1,'Bank and Cash','1234'),
        ('1234 Tax Received','Tax Received',1,'Current Liabilities','1234'),
        ('1234 Product Sales','Product Sales',1,'Income','1234'),
        ('1234 Account Payable','Account Payable',1,'Payable','1234'),
        ('1234 Expenses','Expenses',1,'Expenses','1234'),
        ('1234 Tax Paid','Tax Paid',1,'Current Assets','1234'),
        ('1234 Extra Payment','Extra Payment',1,'Income','1234'),
        ('1234 Product Purchases','Product Purchases',1,'Expenses','1234');


INSERT INTO `journal` (NAME, CODE, TYPE, active) 
VALUES  ('Customer Invoices','INV','Sale',1),
        ('Cash','Cash','Cash',1),
        ('Bank','Bank','Bank',1),
        ('Vendor Bills','BILL','Purchase',1);


INSERT INTO `tax`  (NAME, amount, type_tax_use, active, percent)
VALUES  ('Sales 15%',0.15,'Sale',1,15),
        ('Purchases 20%',0.2,'Purchase',1,20);

INSERT INTO `user`  (login, PASSWORD, NAME, user_type, active)
VALUES ('user','user','User','User', 1);

