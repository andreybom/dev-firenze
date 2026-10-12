CREATE TABLE company (
    id UUID NOT NULL,
    legal_name VARCHAR(200) NOT NULL,
    trade_name VARCHAR(200),
    tax_id VARCHAR(14),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_company PRIMARY KEY (id),
    CONSTRAINT uq_company_tax_id UNIQUE (tax_id),
    CONSTRAINT ck_company_tax_id_length CHECK (tax_id IS NULL OR CHAR_LENGTH(tax_id) = 14)
);

CREATE TABLE development (
    id UUID NOT NULL,
    company_id UUID NOT NULL,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(200) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_development PRIMARY KEY (id),
    CONSTRAINT fk_development_company FOREIGN KEY (company_id) REFERENCES company (id),
    CONSTRAINT uq_development_company_code UNIQUE (company_id, code)
);

CREATE INDEX ix_development_company ON development (company_id);

CREATE TABLE development_block (
    id UUID NOT NULL,
    development_id UUID NOT NULL,
    code VARCHAR(50) NOT NULL,
    name VARCHAR(200),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_development_block PRIMARY KEY (id),
    CONSTRAINT fk_development_block_development FOREIGN KEY (development_id) REFERENCES development (id),
    CONSTRAINT uq_development_block_code UNIQUE (development_id, code)
);

CREATE INDEX ix_development_block_development ON development_block (development_id);

CREATE TABLE lot (
    id UUID NOT NULL,
    development_block_id UUID NOT NULL,
    code VARCHAR(50) NOT NULL,
    status VARCHAR(32) NOT NULL DEFAULT 'AVAILABLE',
    area_square_meters NUMERIC(15, 4),
    list_price NUMERIC(19, 2),
    version BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT pk_lot PRIMARY KEY (id),
    CONSTRAINT fk_lot_development_block FOREIGN KEY (development_block_id) REFERENCES development_block (id),
    CONSTRAINT uq_lot_development_block_code UNIQUE (development_block_id, code),
    CONSTRAINT ck_lot_status CHECK (
        status IN (
            'AVAILABLE',
            'RESERVED',
            'UNDER_CONTRACT',
            'SOLD',
            'BLOCKED',
            'EXCHANGED',
            'INTERNAL_USE',
            'UNAVAILABLE'
        )
    ),
    CONSTRAINT ck_lot_area_positive CHECK (area_square_meters IS NULL OR area_square_meters > 0),
    CONSTRAINT ck_lot_price_nonnegative CHECK (list_price IS NULL OR list_price >= 0)
);

CREATE INDEX ix_lot_development_block ON lot (development_block_id);
CREATE INDEX ix_lot_status ON lot (status);
