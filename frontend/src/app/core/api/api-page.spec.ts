import { buildPageParams } from './api-page';

describe('buildPageParams', () => {
  it('uses the shared defaults', () => {
    const params = buildPageParams();

    expect(params.get('page')).toBe('0');
    expect(params.get('size')).toBe('20');
  });

  it('serializes repeated sorts and explicit filters', () => {
    const params = buildPageParams({
      page: 2,
      size: 50,
      sort: [
        { field: 'status', direction: 'desc' },
        { field: 'code', direction: 'asc' },
      ],
      filters: { status: 'AVAILABLE', active: true, ignored: null },
    });

    expect(params.getAll('sort')).toEqual(['status,desc', 'code,asc']);
    expect(params.get('active')).toBe('true');
    expect(params.get('status')).toBe('AVAILABLE');
    expect(params.has('ignored')).toBe(false);
  });

  it('rejects invalid pagination and parameter names', () => {
    expect(() => buildPageParams({ page: -1 })).toThrowError();
    expect(() => buildPageParams({ size: 101 })).toThrowError();
    expect(() => buildPageParams({ filters: { 'status[eq]': 'AVAILABLE' } })).toThrowError();
  });
});
