import { HttpParams } from '@angular/common/http';

export const DEFAULT_PAGE = 0;
export const DEFAULT_PAGE_SIZE = 20;
export const MAX_PAGE_SIZE = 100;

export type SortDirection = 'asc' | 'desc';

export interface ApiSort {
  readonly field: string;
  readonly direction: SortDirection;
}

export interface PageMetadata {
  readonly number: number;
  readonly size: number;
  readonly totalElements: number;
  readonly totalPages: number;
}

export interface PageResponse<T> {
  readonly items: readonly T[];
  readonly page: PageMetadata;
}

export interface PageQuery {
  readonly page?: number;
  readonly size?: number;
  readonly sort?: readonly ApiSort[];
  readonly filters?: Readonly<Record<string, string | number | boolean | null | undefined>>;
}

export function buildPageParams(query: PageQuery = {}): HttpParams {
  const page = query.page ?? DEFAULT_PAGE;
  const size = query.size ?? DEFAULT_PAGE_SIZE;

  if (!Number.isInteger(page) || page < 0) {
    throw new Error('Page number must be a non-negative integer');
  }
  if (!Number.isInteger(size) || size < 1 || size > MAX_PAGE_SIZE) {
    throw new Error(`Page size must be an integer between 1 and ${MAX_PAGE_SIZE}`);
  }

  let params = new HttpParams().set('page', page).set('size', size);
  for (const sort of query.sort ?? []) {
    if (!/^[A-Za-z][A-Za-z0-9]*$/.test(sort.field)) {
      throw new Error(`Invalid sort field: ${sort.field}`);
    }
    params = params.append('sort', `${sort.field},${sort.direction}`);
  }

  for (const [name, value] of Object.entries(query.filters ?? {}).sort(([a], [b]) =>
    a.localeCompare(b),
  )) {
    if (!/^[A-Za-z][A-Za-z0-9]*$/.test(name)) {
      throw new Error(`Invalid filter name: ${name}`);
    }
    if (value !== null && value !== undefined) {
      params = params.set(name, String(value));
    }
  }

  return params;
}
