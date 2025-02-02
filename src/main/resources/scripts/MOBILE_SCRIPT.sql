CREATE TABLE "UPI_META_MOBILE" (
   	"ID" VARCHAR2(36) NOT NULL,
    "CUSTOMER_NAME" VARCHAR2(100 BYTE),
	"DOB" VARCHAR2(100 BYTE),
	"GENDER" VARCHAR2(10 BYTE),
	"PRIMARY_MOBILE" VARCHAR2(20 BYTE),
	"PRIMARY_IMEI" VARCHAR2(20 BYTE),
	"PRIMARY_NETWORK_PROVIDER" VARCHAR2(20 BYTE),
	"SECONDARY_MOBILE" VARCHAR2(20 BYTE),
	"SECONDARY_IMEI" VARCHAR2(20 BYTE),
	"SECONDARY_NETWORK_PROVIDER" VARCHAR2(20 BYTE),
	"DATETIME_CREATED" TIMESTAMP (3),
	CONSTRAINT "PK_UPI_META_MOBILE" PRIMARY KEY ("ID")
);

CREATE INDEX "UPI_META_MOBILE_INDEX1" ON "UPI_META_MOBILE" ("MOBILE_PRIMARY"); 